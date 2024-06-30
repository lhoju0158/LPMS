package lpms.backend.info;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.IOException;

<<<<<<< Updated upstream
=======
import static lpms.backend.utils.ByteUtils.*;

/**
 * Class representing header information.
 */
>>>>>>> Stashed changes
@Getter @Setter
@ToString
@Slf4j
public class HeaderInfo {
    private String siteId;
    private String sPlant;
    private short systemId;
    private SystemType sType;
    private short eventCh;
    private short totalChNumber;
    private short eventType;
    private String eventDate;
    private short alarmResult;
    private String signalVerificationResult;
    private int samplingRate;
    private short userID;
    private short signalVerificationSelection;
    private int eventDuration;
    private String signalType;
    private float fSensitivity;
    private float fMilsV;
    private int nLength;

    public static enum SystemType {
        LPMS_BIN,
        ALMS,
        RCPVMS,
        IVMS
    }

<<<<<<< Updated upstream
    public static HeaderInfo readHeaderInfo(DataInputStream dis) {
=======
    /**
     * Reads header information from a DataInputStream. (64 * 8 bytes)
     *
     * @param dis the DataInputStream to read from
     * @return the populated HeaderInfo object
     * @throws IOException if an I/O error occurs
     */
    public static HeaderInfo readHeaderInfo(DataInputStream dis) throws IOException {
>>>>>>> Stashed changes
        HeaderInfo headerInfo = new HeaderInfo();
        try {
            byte[] buffer;


<<<<<<< Updated upstream
            buffer = new byte[8];
            dis.readFully(buffer);
            headerInfo.SiteID = new String(buffer).trim();

            if (headerInfo.SiteID.toLowerCase().contains("hanbit")) {
                headerInfo.sPlant = "한빛";
            } else if (headerInfo.SiteID.toLowerCase().contains("hanul")) {
                headerInfo.sPlant = "한울";
=======
        buffer = new byte[8];
        dis.readFully(buffer);
        headerInfo.siteId = new String(buffer).trim();

        if (headerInfo.siteId.toLowerCase().contains("hanbit")) {
            headerInfo.sPlant = "한빛";
        } else if (headerInfo.siteId.toLowerCase().contains("hanul")) {
            headerInfo.sPlant = "한울";
        }

        headerInfo.systemId = dis.readShort();
        headerInfo.sType = SystemType.LPMS_BIN;

        headerInfo.eventCh = readShortLittleEndian(dis);
        headerInfo.totalChNumber = readShortLittleEndian(dis);
        headerInfo.eventType = dis.readShort();

        buffer = new byte[24];
        dis.readFully(buffer);
        headerInfo.eventDate = new String(buffer).trim();

        headerInfo.alarmResult = readShortLittleEndian(dis);

        buffer = new byte[6];
        dis.readFully(buffer);
        headerInfo.signalVerificationResult =new String(buffer).trim();

        headerInfo.samplingRate = readIntLittleEndian(dis);
        headerInfo.userID = dis.readShort();
        headerInfo.signalVerificationSelection = readShortLittleEndian(dis);
        headerInfo.eventDuration = readIntLittleEndian(dis);

        int nSignalType = readShortLittleEndian(dis);

        if (headerInfo.sType == SystemType.LPMS_BIN) {
            switch (nSignalType) {
                case 0:
                    headerInfo.signalType = "BackGround Noise";
                    break;
                case 1:
                    headerInfo.signalType = "Event";
                    break;
                case 2:
                    headerInfo.signalType = "PST";
                    break;
                case 3:
                    headerInfo.signalType = "Impact Test";
                    break;
                case 4:
                    headerInfo.signalType = "Baseline Test";
                    break;
                case 5:
                    headerInfo.signalType = "RCP Trigger";
                    break;
                default:
                    headerInfo.signalType = "Unidentified";
                    break;
>>>>>>> Stashed changes
            }

            headerInfo.System_ID = dis.readShort();
            headerInfo.sType = SystemType.LPMS_BIN;

            headerInfo.Event_Ch = dis.readShort();
            headerInfo.Total_Ch_Number = readShortLittleEndian(dis);
            headerInfo.EventType = dis.readShort();

            buffer = new byte[24];
            dis.readFully(buffer);
            headerInfo.Event_Date = new String(buffer).trim();

            headerInfo.Alarm_Result = readShortLittleEndian(dis);

            buffer = new byte[6];
            dis.readFully(buffer);
            headerInfo.Signal_Verification_Result = new String(buffer).trim();

            headerInfo.Sampling_Rate = readIntLittleEndian(dis);
            headerInfo.User_ID = dis.readShort();
            headerInfo.Signal_Verificaion_Selection = dis.readShort();
            headerInfo.Event_Duration = readIntLittleEndian(dis);

            int nSignalType = readShortLittleEndian(dis);

            if (headerInfo.sType == SystemType.LPMS_BIN) {
                switch (nSignalType) {
                    case 0:
                        headerInfo.Signal_Type = "BackGround Noise";
                        break;
                    case 1:
                        headerInfo.Signal_Type = "Event";
                        break;
                    case 2:
                        headerInfo.Signal_Type = "PST";
                        break;
                    case 3:
                        headerInfo.Signal_Type = "Impact Test";
                        break;
                    case 4:
                        headerInfo.Signal_Type = "Baseline Test";
                        break;
                    case 5:
                        headerInfo.Signal_Type = "RCP Trigger";
                        break;
                    default:
                        headerInfo.Signal_Type = "Unidentified";
                        break;
                }
            } else {
                System.out.println("RG NIMS LPMS_BIN 데이터 파일형식이 아닙니다.");
                return null;
            }

            dis.readShort();
            headerInfo.fSensitivity = readFloatLittleEndian(dis);
            headerInfo.fMilsV = dis.readFloat();



            dis.skipBytes(8 * (64 - 9));


            double itmp = (double) headerInfo.Sampling_Rate * headerInfo.Event_Duration;
            double tmps = itmp / 1000.0;
            headerInfo.nLength = (int) tmps;
            if (itmp < 0) {
                float tmp = (float) (headerInfo.Event_Duration / 1000.0);
                headerInfo.nLength = headerInfo.Sampling_Rate * (int) tmp;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
<<<<<<< Updated upstream
=======

        dis.readShort();
        headerInfo.fSensitivity = readFloatLittleEndian(dis);
        headerInfo.fMilsV = dis.readFloat();


        dis.skipBytes(8 * (64 - 9));


        double itmp = (double) headerInfo.samplingRate * headerInfo.eventDuration;
        double tmps = itmp / 1000.0;
        headerInfo.nLength = (int) tmps;
        if (itmp < 0) {
            float tmp = (float) (headerInfo.eventDuration / 1000.0);
            headerInfo.nLength = headerInfo.samplingRate * (int) tmp;
        }

        headerInfo.nLength++;
>>>>>>> Stashed changes
        return headerInfo;
    }

    public static short readShortLittleEndian(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[2];
        dis.readFully(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getShort();
    }

    public static int readIntLittleEndian(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[4];
        dis.readFully(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }

    public static float readFloatLittleEndian(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[4];
        dis.readFully(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getFloat();
    }



}