package by.tc.task05.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.task05.controller.command.impl.*;
import com.google.common.base.Enums;
import com.google.common.base.Optional;

import javax.annotation.Nullable;


public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GOTOSIGNIN, new GoToSignInPage());
        commands.put(CommandName.GOTOREGISTRATION, new GoToRegistrationPage());
        commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
        commands.put(CommandName.GOTOSEARCHPAGE, new GoToSearchPage());
        commands.put(CommandName.GOTOSTARTERPAGE, new GoToStarterPage());
        commands.put(CommandName.SIGNIN, new SignIn());
        commands.put(CommandName.SIGNOUT, new SignOut());
        commands.put(CommandName.CHANGELANGUAGE, new ChangeLanguage());
        commands.put(CommandName.ACCOUNT, new Account());
        commands.put(CommandName.GOTOEDITUSERINFOPAGE,
                new GoToEditUserInfoPage());
        commands.put(CommandName.EDITUSERINFO, new EditUserInfo());
        commands.put(CommandName.EDITEMAIL, new EditEmail());
        commands.put(CommandName.GOTOEDITEMAILPAGE, new GoToEditEmailPage());
        commands.put(CommandName.EDITPASSWORD, new EditPassword());
        commands.put(CommandName.GOTOEDITPASSWORDPAGE,
                new GoToEditPasswordPage());
        commands.put(CommandName.GOTODELETEACCOUNTPAGE,
                new GoToDeleteAccountPage());
        commands.put(CommandName.DELETEACCOUNT, new DeleteAccount());
        commands.put(CommandName.GOTOSETAVATARPAGE, new GoToSetAvatarPage());
        commands.put(CommandName.SETAVATAR, new SetAvatar());
        commands.put(CommandName.GOTOADDHOTELPAGE, new GoToAddHotelPage());
        commands.put(CommandName.ADDHOTEL, new AddHotel());
        commands.put(CommandName.GOTOHOTELMANAGEMENTPAGE,
                new GoToHotelManagementPage());
        commands.put(CommandName.GOTOSETHOTELICONPAGE,
                new GoToSetHotelIconPage());
        commands.put(CommandName.SETHOTELICON, new SetHotelIcon());
        commands.put(CommandName.GOTODELETEHOTELPAGE,
                new GoToDeleteHotelPage());
        commands.put(CommandName.DELETEHOTEL, new DeleteHotel());
        commands.put(CommandName.GOTOCHANGEHOTELPAGE,
                new GoToChangeHotelPage());
        commands.put(CommandName.CHANGEHOTEL, new ChangeHotel());
        commands.put(CommandName.GOTOADMINSPAGE, new GoToAdminsPage());
        commands.put(CommandName.GOTOADDADMINPAGE, new GoToAddAdminPage());
        commands.put(CommandName.ADDADMIN, new AddAdmin());
        commands.put(CommandName.GOTODELETEADMINPAGE,
                new GoToDeleteAdminPage());
        commands.put(CommandName.DELETEADMIN, new DeleteAdministrator());
        commands.put(CommandName.GOTOHOTELPAGE, new GoToHotelPage());
        commands.put(CommandName.GOTOADDROOMPAGE, new GoToAddRoomPage());
        commands.put(CommandName.ADDROOM, new AddRoom());
        commands.put(CommandName.GOTOCHANGEROOMPAGE, new GoToChangeRoomPage());
        commands.put(CommandName.CHANGEROOM, new ChangeRoom());
        commands.put(CommandName.GOTODELETEROOMPAGE, new GoToDeleteRoomPage());
        commands.put(CommandName.DELETEROOM, new DeleteRoom());
        commands.put(CommandName.GOTOSETROOMICONPAGE, new GoToSetRoomIconPage());
        commands.put(CommandName.SETROOMICON, new SetRoomIcon());
        commands.put(CommandName.GOTOROOMPAGE, new GoToRoomPage());
        commands.put(CommandName.GOTOADDROOMFEATUREPAGE, new GoToAddRoomFeaturePage());
        commands.put(CommandName.ADDROOMFEATURE, new AddRoomFeature());
        commands.put(CommandName.GOTOCHANGEROOMFEATUREPAGE, new GoToChangeRoomFeaturePage());
        commands.put(CommandName.CHANGEROOMFEATURE, new ChangeRoomFeature());
        commands.put(CommandName.GOTODELETEROOMFEATUREPAGE, new GoToDeleteRoomFeaturePage());
        commands.put(CommandName.DELETEROOMFEATURE, new DeleteRoomFeature());
        commands.put(CommandName.GOTOUSERRESERVATIONSPAGE, new GoToUserReservationsPage());
        commands.put(CommandName.BOOK, new Book());
        commands.put(CommandName.ARCHIVERESERVATION, new ArchiveReservation());
        commands.put(CommandName.GOTOCANCELRESERVATIONPAGE, new GoToCancelReservationPage());
    }

    @Nullable
    public Command takeCommand(String name) {
        Optional<CommandName> commandName;
        commandName = Enums.getIfPresent(CommandName.class, name.toUpperCase());
        if (commandName.isPresent()) {
            return commands.get(commandName.get());
        } else {
            return null;
        }
    }

}
