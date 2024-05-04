package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void setJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder sql) {
        // TODO Auto-generated method stub
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        Long areaTo = buildingSearchRequest.getAreaTo();
        if(areaFrom != null || areaTo != null) {
            sql.append("INNER JOIN rentarea r\n"
                    + "ON b.id = r.buildingid\n");
        }
        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId != null) {
            sql.append("INNER JOIN assignmentbuilding a\n"
                    + "ON a.buildingid = b.id\n");
        }
    }


    public void queryNormal(BuildingSearchRequest buildingSearchRequest, StringBuilder where){
        // TODO Auto-generated method stub
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.startsWith("rent") && !fieldName.startsWith("area") && !fieldName.startsWith("type")) {
                    Object value = item.get(buildingSearchRequest);
                    if(value != null && value != "") {
                        if(item.getType().getName().equals("java.lang.String"))
                            where.append("AND b." + fieldName + " LIKE '%" + value + "%' ");
                        else if(item.getType().getName().equals("java.lang.Long")) {
                            where.append("AND b." + fieldName + " = " + value + " ");
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void querySpecial(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
        Long staffId = buildingSearchRequest.getStaffId();
        if (staffId != null) {
            where.append("AND a.staffid = " + staffId + " ");
        }
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        if (rentPriceTo != null) {
            where.append("AND rentprice <= " + rentPriceTo + " ");
        }
        if (rentPriceFrom != null) {
            where.append("AND rentprice >= " + rentPriceFrom + " ");
        }
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        Long areaTo = buildingSearchRequest.getAreaTo();
        if(areaFrom != null) {
            where.append("AND r.value >= " + areaFrom + " ");
        }
        if(areaTo != null) {
            where.append("AND r.value <= " + areaTo + " ");
        }
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if(typeCode != null && typeCode.size() != 0) {
            where.append("AND b.type LIKE '%");
//			for(String x : listRentType) {
//				where.append("'" + x + "',");
//			}
//			where.deleteCharAt(where.length()-1);
//			where.append(") ");
            //java 7
            //where.append("('" + String.join("', '", listRentType) + "') ");

            //java 8
            String where_tmp = typeCode.stream().map(it -> it).collect(Collectors.joining(","));
            where.append(where_tmp);
            where.append("%' ");
        }

    }
    @Override
    public List<BuildingEntity> findAllBuilding(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(buildingSearchRequest))
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }


    @Override
    public int countTotalItem(BuildingSearchRequest buildingSearchRequest) {
        String sql = buildQueryFilter(buildingSearchRequest);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter(BuildingSearchRequest buildingSearchRequest) {
            StringBuilder sql = new StringBuilder("SELECT b.* FROM building b\n");
        setJoin(buildingSearchRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNormal(buildingSearchRequest, where);
        querySpecial(buildingSearchRequest, where);
        where.append("GROUP BY b.id");
        sql.append(where);
        return sql.toString();
    }
}
