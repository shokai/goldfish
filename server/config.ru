require 'rubygems'
require 'sinatra'
require File.dirname(__FILE__)+'/bootstrap'
require File.dirname(__FILE__)+'/main'

set :environemt, :production

run Sinatra::Application
