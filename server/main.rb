before do
  @title = 'goldfish'
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

get '/samples/tcp_chat' do
  @host = env['SERVER_NAME']
  @port = @@conf['chat_port']
  @tag = 'samples/tcp_chat'
  haml 'samples/tcp_chat'.to_sym
end

get '/samples/udp_mouse' do
  @host = env['SERVER_NAME']
  @port = @@conf['mouse_port']
  @tag = 'samples/udp_mouse'
  haml 'samples/udp_mouse'.to_sym
end
