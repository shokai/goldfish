require 'rubygems'
require 'bundler/setup'
require 'rack'
require 'sinatra/reloader' if development?
require 'mongoid'
require 'haml'
require 'yaml'
require 'json'
require 'kconv'
[:models ,:controllers].each do |dir|
  Dir.glob(File.dirname(__FILE__)+"/#{dir}/*.rb").each do |rb|
    puts "loading #{rb}"
    require rb
  end
end

begin
  @@conf = YAML::load open(File.dirname(__FILE__)+'/config.yaml').read
  p @@conf
rescue => e
  STDERR.puts 'config.yaml load error!'
  STDERR.puts e
  exit 1
end

Mongoid.configure{|conf|
  conf.master = Mongo::Connection.new(@@conf['mongo_server'], @@conf['mongo_port']).db(@@conf['mongo_dbname'])
}

def app_root
  "#{env['rack.url_scheme']}://#{env['HTTP_HOST']}#{env['SCRIPT_NAME']}"
end

