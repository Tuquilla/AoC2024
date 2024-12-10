
map = []
File.readlines("productive.txt").each do |line|
  lineArr = line.chomp.to_s.chars.map(&:to_i)
  puts "#{lineArr}"
  map << lineArr
end

def task(map)
  overall_result = 0
  overall_result2 = 0
  map.each_with_index do |line,height|
    line.each_with_index do |value,length|
      if value == 0
        result = []
        searchRecursive(map,height,length, result)
        overall_result += result.uniq.size
        overall_result2 += result.size
      end
    end
  end
  puts "Result task1: #{overall_result}"
  puts "Result task2: #{overall_result2}"
end

def searchRecursive(map, height,length,result)
  if map[height][length] == 9
    #puts "at postion: #{height},#{length}"
    tmp = [height,length]
      result << tmp
    return 
  end
  # up
  if height-1 >= 0 && (map[height-1][length]) == map[height][length]+1
    searchRecursive(map,height-1,length,result)
  end
  # down
  if height+1 < map.size && (map[height+1][length]) ==  map[height][length]+1 
    searchRecursive(map,height+1,length,result)
  end
  # right
  if length+1 < map[height].size && (map[height][length+1]) == map[height][length]+1
    searchRecursive(map,height,length+1,result)
  end
  # left
  if length-1 >= 0 && (map[height][length-1]) == map[height][length]+1
    searchRecursive(map,height,length-1,result)
  end
end



task(map)
