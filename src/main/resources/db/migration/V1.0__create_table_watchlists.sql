create table public.tbl_watchlist (
    id_watchlist serial primary key,
    username varchar(25) NOT NULL,
    name_watchlist varchar(25),
    visibility varchar(25) NOT NULL
)