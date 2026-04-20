require 'jwt'
require 'rack'

module Middleware
  class AdminAuth
    def initialize(app)
      @app = app
    end

    def call(env)
      request = Rack::Request.new(env)

      if request.path.include?("admin")
        token = request.cookies['auth']

        if token
          begin
            hmac_secret = JWTSecret.new.show
            decoded_token = JWT.decode(
              token,
              hmac_secret,
              true,
              { algorithm: 'HS256' }
            )
          rescue JWT::DecodeError => e
            return [401, { 'Content-Type' => 'text/plain' }, ['Invalid or Expired Admin Token']]
          end
        else
          return [401, { 'Content-Type' => 'text/plain' }, ['Missing Admin Authentication Cookie']]
        end
      end

      @app.call(env)
    end
  end
end