
get '/sample' do
  @title = "#{@title} - sample"
  @tag = 'sample'
  haml :sample, :layout => :samples_layout
end

get '/samples/orientation' do
  @title = "#{@title} - orientatino"
  @tag = 'samples/orientation'
  haml :samples_orientation, :layout => :samples_layout
end

get '/samples/tcp_chat' do
  @title = "#{@title} - TCP Chat"
  @host = env['SERVER_NAME']
  @port = @@conf['chat_port']
  @tag = 'samples/tcp_chat'
  haml :samples_tcp_chat, :layout => :samples_layout
end

get '/samples/udp_mouse' do
  @title = "#{@title} - UDP Mouse"
  @host = env['SERVER_NAME']
  @port = @@conf['mouse_port']
  @tag = 'samples/udp_mouse'
  haml :samples_udp_mouse, :layout => :samples_layout
end

get '/samples/udp_touchpad' do
  @title = "#{@title} - UDP TouchPad"
  @host = env['SERVER_NAME']
  @port = @@conf['mouse_port']
  @tag = 'samples/udp_touchpad'
  haml :samples_udp_touchpad, :layout => :samples_layout
end

get '/samples/:name' do
  @name = params[:name]
  @title = "#{@title} - #{@name}"
  haml "samples_#{@name}".to_sym, :layout => false
end
