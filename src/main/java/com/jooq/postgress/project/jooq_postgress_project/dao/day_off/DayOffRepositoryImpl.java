package com.jooq.postgress.project.jooq_postgress_project.dao.day_off;

import com.jooq.postgress.project.jooq_postgress_project.pojo.DayOff;
import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import com.jooq.postgress.project.jooq_postgress_project.tables.Dayoff;
import com.jooq.postgress.project.jooq_postgress_project.tables.Incidents;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional
public class DayOffRepositoryImpl implements DayOffRepository {

    @Autowired
    private DSLContext dsl;

    @Override
    public List<DayOff> getAllDayOff() {
        List<DayOff> posts = new ArrayList<>();
        Result<Record> result = dsl.select().from(Dayoff.DAYOFF).fetch();
        for (Record r : result) {
            posts.add(getDayOffEntity(r));
        }
        return posts;
    }

    @Override
    public void addDayOff(Timestamp timestampDayOff) {

        try {
            dsl.insertInto(Dayoff.DAYOFF)
                    .set(Dayoff.DAYOFF.DAYOFF_, timestampDayOff.toLocalDateTime())
                    .returning(Dayoff.DAYOFF.DAYOFF_)
                    .fetchOne();
        }catch (Exception e){
            System.out.println("Инцидент с id "+ timestampDayOff.toLocalDateTime() + " уже существует");
        }
    }

    @Override
    public void deleteDayOff(DayOff dayOff) {
        dsl.deleteFrom(Dayoff.DAYOFF)
                .where(Dayoff.DAYOFF.DAYOFF_.eq(dayOff.getDayOff().toLocalDateTime()))
                .execute();
    }

    @Override
    public DayOff getDayOff(Timestamp timestampDayOff) {
        try {
            return Objects.requireNonNull(dsl.selectFrom(Dayoff.DAYOFF)
                    .where(Dayoff.DAYOFF.DAYOFF_.eq(timestampDayOff.toLocalDateTime()))
                    .fetchAny())  //здесь определяем, что мы хотим вернуть
                    .into(DayOff.class);
        }catch (NullPointerException e){
            return null;
        }
    }

    private DayOff getDayOffEntity(Record r){
        Timestamp dayOff = r.getValue(Dayoff.DAYOFF.DAYOFF_, Timestamp.class);
        return new DayOff(dayOff);
    }
}
