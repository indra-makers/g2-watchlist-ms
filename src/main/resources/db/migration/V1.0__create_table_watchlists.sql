create table public.tbl_watchlist (
    idWatchlist serial primary key,
    username varchar(25) NOT NULL,
    nameWatchlist varchar(25) NOT NULL,
    visibility bit
)