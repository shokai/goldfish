#!/usr/bin/env ruby
require 'webrick'

port = 8080
doc_root = './'

port = ARGV.shift.to_i unless ARGV.empty?
doc_root = ARGV.shift unless ARGV.empty?

server = WEBrick::HTTPServer.new({
  :DocumentRoot => doc_root,
  :BindAddress => '0.0.0.0',
  :Port => port
})


['INT', 'TERM'].each {|signal|
  Signal.trap(signal){ server.shutdown }
}

server.start
