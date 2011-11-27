before do
  @title = 'goldfish'
end

get '/' do
  haml :index
end
