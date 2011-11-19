require 'rubygems'
require 'sinatra'
require File.dirname(__FILE__)+'/bootstrap'
require File.dirname(__FILE__)+'/main'

set :environment, :development

set :port, 8080
set :server, 'thin'

Sinatra::Application.run
