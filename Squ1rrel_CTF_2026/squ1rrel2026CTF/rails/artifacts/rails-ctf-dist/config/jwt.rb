class JWTSecret
    SECRET = ENV["JWT_SECRET"] || SecureRandom.base64(32).freeze

    def show
        SECRET
    end
end