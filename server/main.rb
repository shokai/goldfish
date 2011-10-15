before do
  @title = 'gynamic'
end

get '/' do
  haml :index
end

get '/t/:tag' do
  @tag = params[:tag]
  haml :tag
end

get '/sample' do
  @tag = 'sample'
  haml :sample
end
