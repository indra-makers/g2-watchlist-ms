create table public.tbl_coinWatchlist (
    id serial primary key,
    id_symbolCoin varchar(25) NOT NULL,
    id_watchlist int NOT NULL,
    foreign key(id_watchlist) references tbl_watchlist(id_watchlist)
)