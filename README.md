# Message-Control
 Remotely send messages by messaging a player.
 
# Usage
 Install the mod and join a server. When a player running this mod is sent a message, they will immediately send the contents of the message back into chat (e.g., `/w Player1 hello world!` outputs `<Player1> hello world!`, and `/w Player1 #goto 1 2 3` will either output `<Player1> #goto 1 2 3`, or, if Baritone is installed, it will catch this command and will instead pathfind to the block at 1, 2, 3.
 
 Other mods that use chat control (mainly utility/cheat clients) can also be controlled with this. (If the prefix is `.`, you could run `.toggle auto-clicker`) I do not condone the use of cheats or hacks on servers where they are not allowed, but these tools can be very useful for private servers. 
 
 This can also be used with Command Blocks or Datapacks, keeping in mind that the sender will be `Server`, which is not added as an owner by default.
# Auth System
 I'm working on an authentication system that can "encrypt commands" (send `/w Player1 12345`, Player1 sends back `<Player1> Hello world! That was a code message!`, etc., useful for giving people access to a single command rather than everything the account can run.), and also have a simple permission system to allow or deny commands other than encrypted messages.
 
# TODO
- Store a list of player names into a file. Use these when testing if a player has "sudo"/all perms.
- Store a list of "encrypted" commands in a file instead of hardcoded if statements, like so:
  - `"12345": "#goto 1 2 3"`
  - `"67890": "#follow player Steve"`
- Add a clientside command to grant and revoke all-access permission, list users, and reload both config files.
  - `/whisper <add|remove|list|reload> [player]`
