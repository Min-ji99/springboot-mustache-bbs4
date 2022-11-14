use `hospital`;

select * from nation_wide_hospitals
where road_name_address LIKE '경기도 수원시%' AND hospital_name LIKE '%피부%';