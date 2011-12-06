before do
  @title = 'goldfish'
end

get '/' do
  haml :index
end

get '/start' do
  haml :start
end
