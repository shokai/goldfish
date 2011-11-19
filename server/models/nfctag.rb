
class NfcTag
  include Mongoid::Document
  field :hex_id, :type => String, :default => ''
  field :url, :type => String, :default => ''
  def to_hash
    {
      :hex_id => id,
      :url, :type => String
    }
  end
end
