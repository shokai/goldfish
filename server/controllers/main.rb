before do
  @title = 'GoldFish'
end

get '/' do
  haml :index
end

get '/start' do
  @title += " - start"
  haml :start
end

get '/about' do
  @title = "#{@title}とは"
  haml :about
end
