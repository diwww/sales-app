// GET запросы

// "/api/shops"
var shops = [{
    "id": 1,
    "name": "Дикси",
    "image_url": "https://dixy.ru/local/templates/dixy_newyear/img/logo.png"
  },
  {
    "id": 2,
    "name": "Перекресток",
    "image_url": "https://static.savings-united.com/shop/18192/logo/logoperekrestok.png"
  }
]

// "/api/shops/1"
var shop = {
  "id": 1,
  "name": "Дикси",
  "image_url": "https://dixy.ru/local/templates/dixy_newyear/img/logo.png"
}

// "/api/shops/1/categories"
var categories = [
  // Здесь для каждого магазина будут выводиться категории.
  // По сути список категорий для всех магазинов должен быть одинаковым,
  // но если в категории нет товаров, то логично исключить ее из списка.
  //
  // Всегда будет категория "Остальное", в которую попадут товары,
  // не попавшие в другие категории.
]

// "/api/shops/1/items"
var items = [{
    "id": 156579,
    "name": "Дыня Торпеда, 1 кг",
    "old_price": 53.32,
    "new_price": 39.99,
    "discount": "-25",
    "conditions": null,
    "till": "2019-02-05",
    "image_url": "https://dixy.ru/upload/iblock/ad5/2000143680.jpg",
  },
  {
    "id": 156579,
    "name": "Дыня Торпеда, 1 кг",
    "old_price": 53.32,
    "new_price": 39.99,
    "discount": "-25",
    "conditions": null,
    "till": "2019-02-05",
    "image_url": "https://dixy.ru/upload/iblock/ad5/2000143680.jpg",
  }
]

// "/api/shoppinglist/1337"
var shoppinglist = [{
    "shop": "Дикси",
    "item_id": 156,
    "image_url": "http://example.com",
    "price": 56.33,
    "title": "Сок Добрый 1л",
    "quantity": 2
  },
  {
    "shop": "Дикси",
    "item_id": 74,
    "image_url": "http://example.com",
    "price": 199.99,
    "title": "Колбаса докторская",
    "quantity": 1
  },
  {
    "shop": "Перекресток",
    "item_id": 68,
    "image_url": "http://example.com",
    "price": 250,
    "title": "Масло сливочное ГОСТ",
    "quantity": 1
  }
]
