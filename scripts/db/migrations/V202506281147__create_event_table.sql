create table if not exists events (
    id uuid primary key default gen_random_uuid(),
    name text not null,
    description text,
    date timestamptz not null,
    partner_id uuid not null references partner(id) on delete cascade,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table if not exists sections (
    id uuid primary key default gen_random_uuid(),
    name text not null,
    event_id uuid not null references events(id) on delete cascade,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table if not exists spots (
    id uuid primary key default gen_random_uuid(),
    location text not null,
    section_id uuid not null references sections(id) on delete cascade,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
)