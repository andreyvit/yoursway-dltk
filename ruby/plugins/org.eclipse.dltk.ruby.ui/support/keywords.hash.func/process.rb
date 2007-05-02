###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################
javafile = '../../src/org/eclipse/dltk/ruby/internal/ui/text/syntax/RubyKeyword.java'

subst = {}
File.open(javafile) do |f|
  f.each_line do |line|
    case line
    when /public static final RubyKeyword (\w+) = new RubyKeyword\("([^"]+)"/
      subst[$2] = $1
    end
  end
end

File.open('keywords.txt', 'w') do |f|
  subst.keys.each { |k| f.puts k }
end

`gperf -k1,3,$ <keywords.txt >hash.c`

minwl, maxwl, minhv, maxhv = 0, 0, 0, 0
values = ''
wordlist = ''
accum = nil

File.open('hash.c') do |f|
  f.each_line do |line|
    case line.strip
    when /#define MIN_WORD_LENGTH (\d+)/
      minwl = $1
    when /#define MAX_WORD_LENGTH (\d+)/
      maxwl = $1
    when /#define MIN_HASH_VALUE (\d+)/
      minhv = $1
    when /#define MAX_HASH_VALUE (\d+)/
      maxhv = $1
    when /static unsigned char asso_values\[\] =/
      accum = values
    when /static const char \* wordlist\[\] =/
      accum = wordlist
    when /\{/
      # skip
    when /\}/
      accum = nil
    else
      accum << line unless accum.nil?
    end
  end
end

wordlist.gsub! /"([^"]*)"/ do |match|
  if $1.empty?
    "null"
  elsif x = subst[$1]
    x
  else
    %Q/"#{$1}"/
  end
end

data = File.open(javafile) {|f| f.read}

data.sub! /(private static final int MIN_WORD_LENGTH = )\d+/ do "#{$1}#{minwl}" end
data.sub! /(private static final int MAX_WORD_LENGTH = )\d+/ do "#{$1}#{maxwl}" end
data.sub! /(private static final int MIN_HASH_VALUE = )\d+/ do "#{$1}#{minhv}" end
data.sub! /(private static final int MAX_HASH_VALUE = )\d+/ do "#{$1}#{maxhv}" end
data.sub! /(private static final byte\[\] asso_values = \{)[^}]*(\})/ do "#{$1}#{values}#{$2}" end
data.sub! /(private static final RubyKeyword\[\] wordlist = \{)[^}]*(\})/ do "#{$1}#{wordlist}#{$2}" end

File.open(javafile, 'w') {|f| f.write data}

