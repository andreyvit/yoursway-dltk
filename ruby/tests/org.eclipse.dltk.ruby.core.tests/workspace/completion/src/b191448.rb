str1 = "asd"
str2 = "abcdef"
while str1 != nil
  str2 = nil if str2.size > 0
  puts str1.methods.sort
  str1 = nil
end
