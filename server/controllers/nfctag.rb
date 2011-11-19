
before '*.json' do
  content_type 'application/json'  
end

get '/tag' do
  @tags = NfcTag.all
  haml :nfctag_index
end

get '/tag/:hex_id.json' do
  @id = params[:hex_id].downcase
  NfcTag.valid?(@id) or throw(:halt, [400, {:error => 'invalid tag'}.to_json])

  @tag = NfcTag.where(:hex_id => @id).first
  @tag or throw(:halt, [404, {:error => 'tag not found'}.to_json])

  @tag.to_hash.to_json
end

post '/tag/:hex_id.json' do
  @id = params[:hex_id].downcase
  @url = params[:url]
  @description = params[:description].toutf8
  @title = params[:title].toutf8

  NfcTag.valid?(@id) or throw(:halt, [400, {:error => 'invalid tag'}.to_json])
  @url =~ /^https?:\/\/.+/ or throw(:halt, [400, {:error => 'invalid URL'}.to_json])

  begin
    @tag = NfcTag.where(:hex_id => @id).first
    @tag = NfcTag.new unless @tag
    @tag.hex_id = @id
    @tag.description = @description
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
  redirect "#{app_root}/tag/#{@id}/edit" unless @tag
  redirect @tag.url
end
