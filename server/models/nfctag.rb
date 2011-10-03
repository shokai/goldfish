
class NfcTag
  include Mongoid::Document
  field :hex_id, :type => String, :default => ''
  field :script, :type => String, :default => ''
  def to_hash
    {
      :hex_id => id,
      :script => script
    }
  end
end
