from flask import Flask, render_template, redirect, url_for, request, flash
from flask_login import LoginManager, login_user, logout_user, login_required, current_user
from models import db, User, GiftCard, Coupon, UserGiftCard
import secrets
import os
from database import init_db

app = Flask(__name__)
app.config['SECRET_KEY'] = 'giftforge-secret-key-1337'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///giftforge.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db.init_app(app)
login_manager = LoginManager()
login_manager.login_view = 'login'
login_manager.init_app(app)

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/signup', methods=['GET', 'POST'])
def signup():
    if request.method == 'POST':
        username = request.form.get('username')
        password = request.form.get('password')
        
        user_exists = User.query.filter_by(username=username).first()
        if user_exists:
            flash('Username already exists.', 'error')
            return redirect(url_for('signup'))
        
        new_user = User(username=username)
        new_user.set_password(password)
        db.session.add(new_user)
        db.session.commit()
        
        login_user(new_user)
        return redirect(url_for('store'))
    return render_template('signup.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form.get('username')
        password = request.form.get('password')
        
        user = User.query.filter_by(username=username).first()
        if user and user.check_password(password):
            login_user(user)
            return redirect(url_for('store'))
        else:
            flash('Invalid username or password.', 'error')
    return render_template('login.html')

@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('index'))

@app.route('/store')
@login_required
def store():
    gift_cards = GiftCard.query.all()
    return render_template('store.html', gift_cards=gift_cards)

@app.route('/buy/<int:card_id>', methods=['POST'])
@login_required
def buy(card_id):
    card = GiftCard.query.get_or_404(card_id)
    if current_user.balance >= card.price:
        current_user.balance -= card.price
        
        if "Flag" in card.name:
            code = os.getenv("FLAG") or "VBD{F4k3_fl4g_f0r_t3st1ng}"
        else:
            code = f"GF-{secrets.token_hex(8)}"
            
        new_user_card = UserGiftCard(user_id=current_user.id, gift_card_id=card.id, code=code)
        db.session.add(new_user_card)
        db.session.commit()
        flash(f'Successfully purchased {card.name}!', 'success')
    else:
        flash('Insufficient balance.', 'error')
    return redirect(url_for('store'))

import unicodedata

@app.route('/redeem', methods=['GET', 'POST'])
@login_required
def redeem():
    if request.method == 'POST':
        code = request.form.get('code', '').strip()
        
        if code == "GIFT500":
            flash('This special offer has expired.', 'error')
            return redirect(url_for('redeem'))
        code = "".join(c for c in unicodedata.normalize('NFKD', code) if not unicodedata.combining(c)).upper()
        
        if code == "GIFT500":
            current_user.balance += 500.0
            db.session.commit()
            flash('500 credits added to your account.', 'success')
            return redirect(url_for('store'))
            
        coupon = Coupon.query.filter_by(code=code).first()
        if coupon:
            if coupon.is_expired:
                flash('This coupon has expired.', 'error')
            else:
                current_user.balance += 10.0
                db.session.commit()
                flash('Coupon redeemed! $10 added to your balance.', 'success')
                return redirect(url_for('store'))
        else:
            flash('Invalid coupon code.', 'error')
            
    return render_template('redeem.html')

@app.route('/profile')
@login_required
def profile():
    return render_template('profile.html')

if __name__ == '__main__':
    init_db()
    app.run(host='0.0.0.0', port=5000, debug=False)
