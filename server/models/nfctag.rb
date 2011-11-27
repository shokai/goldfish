
class NfcTag
  include Mongoid::Document
  field :hex_id, :type => String, :default => ''
  field :url, :type => String, :default => @@conf['default_redirect_url']
  field :title, :type => String, :default => ''
  def to_hash
    {
      :id => hex_id,
      :url => url,
      :title => title
    }
  end

  def NfcTag.valid?(tag)
    tag =~ /^[a-z\d]+$/
  end
end
