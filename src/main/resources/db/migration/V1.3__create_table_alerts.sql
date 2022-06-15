create table public.tbl_alerts(
    id_alert serial primary key,
    id_symbol_coin varchar(25) NOT NULL,
    price numeric NOT NULL,
    username varchar(25) NOT NULL,
    notified varchar(25) NOT NULL
)