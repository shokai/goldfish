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

get '/jumptag' do
  haml :jumptag
end

get '/tutorial/:name' do
  @name = params[:name]
  @title = "#{@title} チュートリアル"
  haml "tutorial_#{@name}".to_sym
end
