import dao.AirplaneDao;
import dao.impl.AirplaneDaoImpl;
import dao.CrewDao;
import dao.impl.CrewDaoImpl;
import domain.Airplane;
import domain.Crew;
import util.JdbcUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        demo();
    }

    private static void demo() {
        AirplaneDao airplaneDao = new AirplaneDaoImpl();
        CrewDao crewDao = new CrewDaoImpl();
        JdbcUtil.initTablesInDB();
        JdbcUtil.populateTablesInDB();


        List<Airplane> airplanes = airplaneDao.findAll();
        for (Airplane airplane : airplanes) {
            System.out.println(airplane);
        }

        Crew crew1 = crewDao.findById(1);
        Crew crew2 = crewDao.findById(2);
        Crew crew3 = crewDao.findById(3);

        System.out.println(crew1);
        System.out.println(crew2);
        System.out.println(crew3);

        System.out.println(airplaneDao.findByCrew(crew1));

    }

}
