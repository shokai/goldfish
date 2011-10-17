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

get '/samples/orientation' do
  @tag = 'samples/orientation'
  haml 'samples/orientation'.to_sym
end
