# This file should ensure the existence of records required to run the application in every environment (production,
# development, test). The code here should be idempotent so that it can be executed at any point in every environment.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Example:
#
#   ["Action", "Comedy", "Drama", "Horror"].each do |genre_name|
#     MovieGenre.find_or_create_by!(name: genre_name)
#   end

Post.destroy_all
Post.create!(id: 1, content: ENV["FLAG"] || "test_flag")
Post.create!(id: 2, content: "Check out my blog!")
Post.create!(id: 3, content: "I split my posts into multiple rows,")
Post.create!(id: 4, content: "so that way you can read them one by one.")
