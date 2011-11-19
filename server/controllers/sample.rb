
get '/sample' do
  @tag = 'sample'
  haml :sample
end

get '/samples/orientation' do
  @tag = 'samples/orientation'
  haml :samples_orientation
end

get '/samples/tcp_chat' do
  @host = env['SERVER_NAME']
  @port = @@conf['chat_port']
  @tag = 'samples/tcp_chat'
  haml :samples_tcp_chat
end

get '/samples/udp_mouse' do
  @host = env['SERVER_NAME']
  @port = @@conf['mouse_port']
  @tag = 'samples/udp_mouse'
  haml :samples_udp_mouse
end

get '/samples/udp_touchpad' do
  @host = env['SERVER_NAME']
  @port = @@conf['mouse_port']
  @tag = 'samples/udp_touchpad'
  haml :samples_udp_touchpad
end
