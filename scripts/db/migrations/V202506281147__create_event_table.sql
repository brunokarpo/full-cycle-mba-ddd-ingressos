create table if not exists event (
    id uuid primary key default gen_random_uuid(),
    name text not null,
    description text,
    date timestamptz not null,
    partner_id uuid not null references partner(id) on delete cascade,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table if not exists section (
    id uuid primary key default gen_random_uuid(),
    name text not null,
    event_id uuid not null references event(id) on delete cascade,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table if not exists spot (
    id uuid primary key default gen_random_uuid(),
    location text not null,
    section_id uuid not null references section(id) on delete cascade,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
)