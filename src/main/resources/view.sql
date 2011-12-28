create view AUTH as 
    select apu.username as USERNAME , apu.password as PASSWORD, roleName as GROUP_NAME 
    from APPUSER apu join APPROLE apr on apu.approle_id = apr.id