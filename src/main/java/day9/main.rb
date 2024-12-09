input = File.open("productive.txt")

input = input.each_char.map(&:to_i)

def switch(empty_spaces, values)
  values.each_with_index do |value,value_index|
    empty_spaces.each_with_index do |empty_space,empty_space_index|
      if empty_space == '.'
        empty_spaces[empty_space_index] = value
        values[value_index] = '.'
        break
      end
    end
  end
end

def task1(input)
  decoded = []

  out_index = 0
  input.each_with_index do |value, index|
    if index%2 == 0
      tmp = Array.new(value, out_index)
      decoded.concat(tmp)
      out_index += 1
    else
      tmp = Array.new(value, '.')
      decoded.concat(tmp)
    end
  end
  
  start_back = decoded.size()-1
  start_front = 0
  result = 0
  while(true)
    if start_front >= start_back
      break;
    end
    if decoded[start_front] == '.' && decoded[start_back] == '.'
      start_back -= 1
    elsif decoded[start_front] == '.'
      decoded[start_front] = decoded[start_back]
      decoded[start_back] = '.'
      start_back -= 1
      start_front += 1
    else
      start_front += 1
    end
  end
  decoded.each_with_index do |value, index|
    if value == '.'
      break
    end
    result += decoded[index] * index
  end
  puts "Result task1: #{result}"
end

def task2(input)
  decoded = []

  out_index = 0
  input.each_with_index do |value, index|
    if index%2 == 0
      tmp = Array.new(value, out_index)
      decoded.concat(tmp)
      out_index += 1
    else
      tmp = Array.new(value, '.')
      decoded.concat(tmp)
    end
  end
  
  start_back = decoded.size()-1
  start_front = 0
  result = 0

  subarrays = decoded.chunk_while { |i, j| i == j }.to_a
  start_index = -1
  last_index = subarrays.size

  subarrays.reverse_each do |values|
    last_index -= 1
    if !values.include?('.')
      subarrays.each_with_index do |empty_spaces, empty_space_index|
        if empty_spaces.include?('.') && empty_spaces.count('.') >= values.size && empty_space_index <= last_index
          switch(empty_spaces, values)
          break
        end
      end
    end
  end

  # count the result
  subarrays = subarrays.flatten
  subarrays.each_with_index do |value, index|
    if value != '.'
      result += value * index
    end
  end
  puts "Result task2: #{result}"
end

task1(input)
task2(input)