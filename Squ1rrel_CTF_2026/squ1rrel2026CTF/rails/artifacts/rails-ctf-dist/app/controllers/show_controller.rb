class ShowController < ApplicationController
  def index
    @resource = params[:resource]

    # If you want to add functionality to this endpoint, add
    # files in modules/
    resource_name = @resource + "Module"
    render json: { data: resource_name.constantize.new.show }
  end
end