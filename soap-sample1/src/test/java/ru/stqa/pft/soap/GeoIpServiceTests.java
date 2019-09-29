package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {
  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
  }
}
