
class NfcTag
  include Mongoid::Document
  field :hex_id, :type => String, :default => ''
  field :url, :type => String, :default => ''
  field :title, :type => String, :default => ''
  field :description, :type => String, :default => ''
  def to_hash
    {
      :hex_id => hex_id,
      :url => url,
      :title => title,
      :description => description
    }
  end

  def NfcTag.valid?(tag)
    tag =~ /^[a-z\d]+$/
  end
end
