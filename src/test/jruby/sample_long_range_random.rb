require 'java'

import 'com.g414.dgen.range.LongRangeBuilder'
import 'com.g414.dgen.range.UuidRange'
import 'java.util.Random'

r = LongRangeBuilder.new(100, 103).withRandom(Random.new).build()

r.each do |n|
  puts n
end

q = UuidRange.new(r)

q.each do |n|
  puts n
end
