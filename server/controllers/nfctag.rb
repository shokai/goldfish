
before '*.json' do
  content_type 'application/json'  
end

get '/tag' do
  @title = "#{@title}/tag"
  @tags = NfcTag.all.asc(:hex_id)
  haml :nfctag_index
end

get '/tag/:hex_id.json' do
  @id = params[:hex_id]
  NfcTag.valid?(@id) or throw(:halt, [400, {:error => 'invalid tag'}.to_json])

  @tag = NfcTag.find_by_hex_id @id
  @tag or throw(:halt, [404, {:error => 'tag not found'}.to_json])

  @tag.to_hash.to_json
end

post '/tag/:hex_id.json' do
  @id = params[:hex_id].downcase
  @url = params[:url]
  @title = params[:title].toutf8

  if @url.size > 0  
    @url =~ /^https?:\/\/.+/ or throw(:halt, [400, {:error => 'invalid URL'}.to_json])
    @url =~ /^#{app_root}\/tag\// and throw(:halt, [400, {:error => 'invalid URL'}.to_json])
  end
  NfcTag.valid?(@id) or throw(:halt, [400, {:error => 'invalid tag'}.to_json])

  begin
    @tag = NfcTag.where(:hex_id => @id).first
    @tag = NfcTag.new unless @tag
    @tag.hex_id = @id
    @tag.url = @url
    @tag.title = @title
    @tag.save
    status 200
    @mes = @tag.to_hash.to_json
  rescue => e
    STDERR.puts e
    status 500
    @mes = {:error => 'internal server error'}.to_json
  end
end

delete '/tag/:hex_id.json' do
  @id = params[:hex_id].downcase
  @tag = NfcTag.where(:hex_id => @id).first
  unless @tag
    status 404
    @mes = {:error => 'tag not found'}.to_json
  else
    begin
      @tag.delete
      status 200
      @mes = @tag.to_hash.to_json
    rescue => e
      STDERR.puts e
      status 500
      @mes = {:error => 'delete error'}.to_json
    end
  end
end

get '/tag/:hex_id/edit' do
  @id = params[:hex_id].downcase
  NfcTag.valid?(@id) or throw(:halt, [400, 'invalid tag'])

  @tag = NfcTag.where(:hex_id => @id).first
  @tag = NfcTag.new unless @tag
  haml :nfctag
end

get '/tag/:hex_id' do
  @id = params[:hex_id].downcase
  NfcTag.valid?(@id) or throw(:halt, [400, 'invalid tag'])
  @tag = NfcTag.where(:hex_id => @id).first
  redirect "/tag/#{@id}/edit" if !@tag or @tag.url.size < 1
  redirect @tag.url
end
