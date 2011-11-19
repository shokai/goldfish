#!/usr/bin/env ruby
require 'rubygems'
require 'ArgsParser'
require 'eventmachine'
require 'java'
import 'java.awt.Robot'
import 'java.awt.event.InputEvent'

parser = ArgsParser.parser
parser.bind(:port, :p, 'port', 8083)
parser.bind(:help, :h, 'show help')

first, params = parser.parse(ARGV)
if parser.has_option(:help)
  puts parser.help
  puts "e.g.  jruby udp-mouse.rb --port 8083"
  exit 1
end

@@robot = Robot.new
@@x = 0
@@y = 0
@@last = Time.now.to_f

class Receiver < EM::Connection
  def receive_data data
    data.strip!
    # port, host = Socket.unpack_sockaddr_in get_peername
    # puts "#{host}:#{port} - #{data}"
    begin
      if data =~ /^\-?[\d\.]+,\-?[\d\.]+$/
        @@x, @@y = data.split(',').map{|i| i.to_f }
        @@last = Time.now.to_f
      else
        if data == 'left_press'
          @@robot.mouse_press InputEvent::BUTTON1_MASK
        elsif data == 'left_release'
          @@robot.mouse_release InputEvent::BUTTON1_MASK
        elsif data == 'right_press'
          @@robot.mouse_press InputEvent::BUTTON3_MASK
        elsif data == 'right_release'
          @@robot.mouse_release InputEvent::BUTTON3_MASK
        elsif data == 'center_press'
          @@robot.mouse_press InputEvent::BUTTON2_MASK
        elsif data == 'center_release'
          @@robot.mouse_release InputEvent::BUTTON2_MASK
        end
      end
    rescue => e
      STDERR.puts e
    end
  end
end

EM::run do
  puts "mouse server start - UDP port:#{params[:port].to_i}"
  EM::open_datagram_socket('0.0.0.0', params[:port].to_i, Receiver)
  EM::defer do
    loop do
      mouse = java.awt.MouseInfo.pointer_info.location
      if Time.now.to_f - @@last < 0.5
        @@robot.mouse_move(mouse.x+@@x, mouse.y+@@y)
      end
      sleep 0.01
    end
  end
end
