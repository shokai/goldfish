
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

  def self.valid?(tag)
    tag =~ /^[a-z\d]+$/i
  end

  def self.find_by_hex_id(hex_id)
    NfcTag.where(:hex_id => hex_id.downcase).first
  end
end
