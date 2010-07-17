require 'java'

import 'com.g414.dgen.range.LongRangeBuilder'
import 'com.g414.dgen.range.UuidRange'

r = LongRangeBuilder.new(0, 10).build()

r.each do |n|
  puts n
end

q = UuidRange.new(r)

q.each do |n|
  puts n
end
