select d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME
from developers d, 
(select sum(skillcodes.code) as code from skillcodes where skillcodes.name in ('C#', 'Python')) c
where (d.skill_code & c.code) > 0
order by d.id