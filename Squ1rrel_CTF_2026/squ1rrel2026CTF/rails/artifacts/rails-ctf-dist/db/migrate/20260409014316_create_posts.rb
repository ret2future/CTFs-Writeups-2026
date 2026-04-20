class CreatePosts < ActiveRecord::Migration[8.1]
  def change
    create_table :posts do |t|
      t.string :content, null: false
      t.timestamps
    end
  end
end
