# Paypal-SpringBoot-Api
This is a simple API developed in spring boot exposing endpoint which one can use to incorporate payments via Paypal in their projects. 

# Make payment
POST /make/payment?total=0&currency="KSH"&paymentMethod="credit_card"&paymentIntent="sale"&description="description"&cancelUrl="cancelUrl"&successUrl="successurl"

#Complete payment
POST /complete/payment?paymentId=paymentId&payerId=paymentId
