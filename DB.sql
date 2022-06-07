create database bookstore;

CREATE TABLE billing_address
(
    id                      BIGINT        NOT NULL,
    billing_address_name    NVARCHAR(255) NOT NULL,
    billing_address_street1 NVARCHAR(255) NOT NULL,
    billing_address_street2 NVARCHAR(255) NULL,
    billing_address_city    NVARCHAR(255) NOT NULL,
    billing_address_state   NVARCHAR(255) NOT NULL,
    billing_address_country NVARCHAR(255) NOT NULL,
    billing_address_zipcode NVARCHAR(255) NOT NULL,
    order_id                BIGINT        NOT NULL,
    CONSTRAINT pk_billingaddress PRIMARY KEY (id)
);

ALTER TABLE billing_address
    ADD CONSTRAINT FK_BILLINGADDRESS_ON_ORDER FOREIGN KEY (order_id) REFERENCES user_order (id);

CREATE TABLE book
(
    id               BIGINT        NOT NULL,
    title            NVARCHAR(255) NOT NULL,
    author           NVARCHAR(255) NOT NULL,
    publisher        NVARCHAR(255) NOT NULL,
    publication_date NVARCHAR(255) NOT NULL,
    language         NVARCHAR(255) NOT NULL,
    number_of_pages  INT           NOT NULL,
    shipping_weight  DOUBLE        NOT NULL,
    price            DOUBLE        NOT NULL,
    active           BIT(1)        NOT NULL,
    description      NVARCHAR(255) NOT NULL,
    in_stock_number  INT           NOT NULL,
    image_show       BLOB          NOT NULL,
    category_id      BIGINT        NOT NULL,
    CONSTRAINT pk_book PRIMARY KEY (id)
);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

CREATE TABLE book_to_cart_item
(
    id           BIGINT NOT NULL,
    book_id      BIGINT NOT NULL,
    cart_item_id BIGINT NOT NULL,
    CONSTRAINT pk_booktocartitem PRIMARY KEY (id)
);

ALTER TABLE book_to_cart_item
    ADD CONSTRAINT FK_BOOKTOCARTITEM_ON_BOOK FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE book_to_cart_item
    ADD CONSTRAINT FK_BOOKTOCARTITEM_ON_CART_ITEM FOREIGN KEY (cart_item_id) REFERENCES cart_item (id);

CREATE TABLE cart_item
(
    id               BIGINT  NOT NULL,
    qty              INT     NOT NULL,
    subtotal         DECIMAL NOT NULL,
    book_id          BIGINT  NOT NULL,
    shopping_cart_id BIGINT  NOT NULL,
    order_id         BIGINT  NOT NULL,
    CONSTRAINT pk_cartitem PRIMARY KEY (id)
);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_CARTITEM_ON_BOOK FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_CARTITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES user_order (id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_CARTITEM_ON_SHOPPING_CART FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id);

CREATE TABLE coupon_import
(
    coupon_import_id BIGINT AUTO_INCREMENT NOT NULL,
    date_import      date                  NOT NULL,
    price_book       INT                   NOT NULL,
    number_import    INT                   NOT NULL,
    total_price      INT                   NOT NULL,
    book_id          BIGINT                NOT NULL,
    CONSTRAINT pk_coupon_import PRIMARY KEY (coupon_import_id)
);

ALTER TABLE coupon_import
    ADD CONSTRAINT FK_COUPON_IMPORT_ON_BOOK FOREIGN KEY (book_id) REFERENCES book (id);

CREATE TABLE user_order
(
    id                  BIGINT        NOT NULL,
    order_date          datetime      NOT NULL,
    shipping_date       datetime      NOT NULL,
    shipping_method     NVARCHAR(255) NOT NULL,
    order_status        NVARCHAR(255) NOT NULL,
    order_total         DECIMAL       NOT NULL,
    shipping_address_id BIGINT        NOT NULL,
    billing_address_id  BIGINT        NOT NULL,
    payment_id          BIGINT        NOT NULL,
    user_id             BIGINT        NOT NULL,
    employee_id         BIGINT        NOT NULL,
    CONSTRAINT pk_user_order PRIMARY KEY (id)
);

ALTER TABLE user_order
    ADD CONSTRAINT FK_USER_ORDER_ON_BILLINGADDRESS FOREIGN KEY (billing_address_id) REFERENCES billing_address (id);

ALTER TABLE user_order
    ADD CONSTRAINT FK_USER_ORDER_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES payment (id);

ALTER TABLE user_order
    ADD CONSTRAINT FK_USER_ORDER_ON_SHIPPINGADDRESS FOREIGN KEY (shipping_address_id) REFERENCES shipping_address (id);

ALTER TABLE user_order
    ADD CONSTRAINT FK_USER_ORDER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE payment
(
    id           BIGINT        NOT NULL,
    type         NVARCHAR(255) NOT NULL,
    card_name    NVARCHAR(255) NOT NULL,
    card_number  NVARCHAR(255) NOT NULL,
    expiry_month INT           NOT NULL,
    expiry_year  INT           NOT NULL,
    cvc          INT           NOT NULL,
    holder_name  NVARCHAR(255) NOT NULL,
    order_id     BIGINT        NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_ORDER FOREIGN KEY (order_id) REFERENCES user_order (id);

CREATE TABLE sales
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    book_id  BIGINT                NOT NULL,
    quantity INT                   NOT NULL,
    month    INT                   NOT NULL,
    year     INT                   NOT NULL,
    CONSTRAINT pk_sales PRIMARY KEY (id)
);

CREATE TABLE shipping_address
(
    id                       BIGINT        NOT NULL,
    shipping_address_name    NVARCHAR(255) NOT NULL,
    shipping_address_street1 NVARCHAR(255) NOT NULL,
    shipping_address_street2 NVARCHAR(255) NULL,
    shipping_address_city    NVARCHAR(255) NOT NULL,
    shipping_address_state   NVARCHAR(255) NOT NULL,
    shipping_address_country NVARCHAR(255) NOT NULL,
    shipping_address_zipcode NVARCHAR(255) NOT NULL,
    order_id                 BIGINT        NOT NULL,
    CONSTRAINT pk_shippingaddress PRIMARY KEY (id)
);

ALTER TABLE shipping_address
    ADD CONSTRAINT FK_SHIPPINGADDRESS_ON_ORDER FOREIGN KEY (order_id) REFERENCES user_order (id);

CREATE TABLE shopping_cart
(
    id          BIGINT  NOT NULL,
    grand_total DECIMAL NOT NULL,
    user_id     BIGINT  NOT NULL,
    CONSTRAINT pk_shoppingcart PRIMARY KEY (id)
);

ALTER TABLE shopping_cart
    ADD CONSTRAINT FK_SHOPPINGCART_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE user_billing
(
    id                   BIGINT        NOT NULL,
    user_billing_name    NVARCHAR(255) NOT NULL,
    user_billing_street1 NVARCHAR(255) NOT NULL,
    user_billing_street2 NVARCHAR(255) NULL,
    user_billing_city    NVARCHAR(255) NOT NULL,
    user_billing_state   NVARCHAR(255) NOT NULL,
    user_billing_country NVARCHAR(255) NOT NULL,
    user_billing_zipcode NVARCHAR(255) NOT NULL,
    user_payment_id      BIGINT        NOT NULL,
    CONSTRAINT pk_userbilling PRIMARY KEY (id)
);

ALTER TABLE user_billing
    ADD CONSTRAINT FK_USERBILLING_ON_USERPAYMENT FOREIGN KEY (user_payment_id) REFERENCES user_payment (id);

CREATE TABLE user_payment
(
    id              BIGINT        NOT NULL,
    type            NVARCHAR(255) NOT NULL,
    card_name       NVARCHAR(255) NOT NULL,
    card_number     NVARCHAR(255) NOT NULL,
    expiry_month    INT           NOT NULL,
    expiry_year     INT           NOT NULL,
    cvc             INT           NOT NULL,
    holder_name     NVARCHAR(255) NOT NULL,
    default_payment BIT(1)        NOT NULL,
    user_id         BIGINT        NOT NULL,
    CONSTRAINT pk_userpayment PRIMARY KEY (id)
);

ALTER TABLE user_payment
    ADD CONSTRAINT FK_USERPAYMENT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE user_shipping
(
    id                    BIGINT        NOT NULL,
    user_shipping_name    NVARCHAR(255) NOT NULL,
    user_shipping_street1 NVARCHAR(255) NOT NULL,
    user_shipping_street2 NVARCHAR(255) NULL,
    user_shipping_city    NVARCHAR(255) NOT NULL,
    user_shipping_state   NVARCHAR(255) NOT NULL,
    user_shipping_country NVARCHAR(255) NOT NULL,
    user_shipping_zipcode NVARCHAR(255) NOT NULL,
    user_shipping_default BIT(1)        NOT NULL,
    user_id               BIGINT        NOT NULL,
    CONSTRAINT pk_usershipping PRIMARY KEY (id)
);

ALTER TABLE user_shipping
    ADD CONSTRAINT FK_USERSHIPPING_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE user
(
    id         BIGINT        NOT NULL,
    username   NVARCHAR(255) NOT NULL,
    password   NVARCHAR(255) NOT NULL,
    first_name NVARCHAR(255) NOT NULL,
    last_name  NVARCHAR(255) NOT NULL,
    email      VARCHAR(255)  NOT NULL,
    phone      VARCHAR(255)  NOT NULL,
    enabled    BIT(1)        NOT NULL,
    employee_id BIGINT NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    role_id INT          NOT NULL,
    name    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id INT    NOT NULL
);

ALTER TABLE user_role
    ADD CONSTRAINT FK_USER_ROLE_ON_ROLE FOREIGN KEY (role_id) REFERENCES `role` (role_id);

ALTER TABLE user_role
    ADD CONSTRAINT FK_USER_ROLE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE employees
(
    id           BIGINT        NOT NULL PRIMARY KEY,
    CI_card      INT           NOT NULL,
    fullname     NVARCHAR(255) NOT NULL,
    phone_number INT           NOT NULL,
    address      TEXT          NOT NULL,
    date_start   DATE          NOT NULL,
    salary       DECIMAL       NOT NULL
);

ALTER TABLE user_order
    ADD CONSTRAINT FK_USER_ORDER_ON_EMPLOYEES FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ROLE_ON_EMPLOYEES FOREIGN KEY (employee_id) REFERENCES employees (id);

CREATE TABLE categories
(
    id            BIGINT NOT NULL,
    category_name VARCHAR(255)
)