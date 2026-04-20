class Admin::PostsController < ApplicationController
  def index
    id = params[:id]
    if id == "1"
      raise "This post is reserved, and cannot be viewed."
    end

    post = Post.where(id: params[:id]).first
    render json: { data: post }
  end
end