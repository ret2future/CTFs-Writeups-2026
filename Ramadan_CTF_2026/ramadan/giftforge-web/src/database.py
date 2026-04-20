from flask import Flask
from models import db, GiftCard, Coupon, User
import os

def init_db():
    app = Flask(__name__)
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///giftforge.db'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    
    db.init_app(app)
    
    with app.app_context():
        db_path = os.path.join(app.instance_path, 'giftforge.db')
        if os.path.exists(db_path):
            os.remove(db_path)
        
        if not os.path.exists(app.instance_path):
            os.makedirs(app.instance_path)
        
        db.create_all()
        
        cards = [
            GiftCard(name="Amazon $50", price=50.0, description="Standard Amazon Gift Card", category="Shopping", image_url="images/amazon.png"),
            GiftCard(name="Steam $20", price=20.0, description="Gaming credits for Steam", category="Gaming", image_url="images/steam.png"),
            GiftCard(name="Xbox Game Pass", price=15.0, description="1 Month of Game Pass", category="Gaming", image_url="images/xbox.png"),
            GiftCard(name="The Secret Flag", price=1337.0, description="The ultimate gift card for hackers", category="Secret", image_url="images/secret.png")
        ]
        db.session.bulk_save_objects(cards)
        
        coupons = [
            Coupon(code="GIFT500", is_expired=True),
            Coupon(code="WELCOME10", is_expired=False)
        ]
        db.session.bulk_save_objects(coupons)
        
        db.session.commit()
        print("Database initialized and seeded.")

if __name__ == "__main__":
    init_db()
