function addToCart(name, price, img) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  cart.push({ name: name, price: price, img: img });
  localStorage.setItem("cart", JSON.stringify(cart));

  updateCartCount();
  alert(name + " added to cart!");
}

function showCart() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  let container = document.getElementById("cart-items");
  container.innerHTML = "";

  if (cart.length === 0) {
    container.innerHTML = "<p>Your cart is empty.</p>";
    updateCartCount();
    return;
  }

  let total = 0;

  cart.forEach((item, index) => {
    total += item.price;
    container.innerHTML += `
      <div style="border:1px solid #ccc; padding:10px; margin:10px; display:flex; align-items:center; gap:10px;">
        <img src="${item.img}" alt="${item.name}" style="width:60px; height:60px; object-fit:cover;">
        <div>
          <h5>${item.name}</h5>
          <p>₹${item.price}</p>
          <button onclick="removeFromCart(${index})" class="btn btn-danger btn-sm">Remove</button>
        </div>
      </div>
    `;
  });

  container.innerHTML += `
    <h4 style="margin-top:20px;">Total: ₹${total}</h4>
  `;

  updateCartCount();
}

function removeFromCart(index) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  cart.splice(index, 1);
  localStorage.setItem("cart", JSON.stringify(cart));
  showCart();
}

// 🛒 Update cart counter in navbar
function updateCartCount() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  let countElem = document.getElementById("cart-count");
  if (countElem) {
    countElem.textContent = cart.length;
  }
}

// Run on page load so counter stays updated
window.onload = function() {
  updateCartCount();
  if (document.getElementById("cart-items")) {
    showCart();
  }
};
