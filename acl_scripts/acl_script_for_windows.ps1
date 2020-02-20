# Ovu skriptutreba pokretati kao Administrator

$group = "AdminGroup"
$groupUsers = "Users"
# $user = "admin1"
$user = "admin"

function Strings-Contains {
 foreach($name in $args[0]) {
	if($name -Match $args[1]) {
		return $true
	}
 }
 
 return $false;
}

$GroupExists = $true
Try { Get-LocalGroup -Name $group -ErrorAction Stop }
Catch { $GroupExists = $false }
if ($GroupExists -eq $false) { 
	New-LocalGroup -Name $group
}

# Samo proveravamo da li vec postoji ovaj user 
$userExist =(Get-LocalUser).Name -Contains $user
if(-Not $userExist) {
	$SecureStringPassword = ConvertTo-SecureString $user -AsPlainText -Force
    New-LocalUser -Name $user -Password $SecureStringPassword
	Write-Host "Kreiran novi lokalni User"
}

$userInGroupUsers = Strings-Contains (Get-LocalGroupMember $groupUsers).Name $user
if (-Not $userInGroupUsers) {
	Add-LocalGroupMember -Group $groupUsers -Member $user  # najpre ga dodajemo u Users grupu da bismo mogli da se ulogujemo na ovaj nalog
} 

$userInGroup = Strings-Contains (Get-LocalGroupMember $group).Name $user
if (-Not $userInGroup) {
	Add-LocalGroupMember -Group $group -Member $user
}

$pathToJar = "..\payment_microservice\PaymentMicroservice\target\PaymentMicroservice-0.0.1-SNAPSHOT.jar"
$aclForJar = Get-Acl $pathToJar

# $AccessRule = New-Object System.Security.AccessControl.FileSystemAccessRule($user,"Read","Allow")
$AccessRuleForJar1 = New-Object System.Security.AccessControl.FileSystemAccessRule($group, 'Read', 'Allow')
$aclForJar.SetAccessRule($AccessRuleForJar1) # ovde se koristi SetAccessRule da bi ponistio prethodne AccessRule-ove, a onda posle se koristi AddAccessRule

$AccessRuleForJar2 = New-Object System.Security.AccessControl.FileSystemAccessRule($group, 'Write', 'Allow')
$aclForJar.AddAccessRule($AccessRuleForJar2)


$pathToResources = "..\payment_microservice\PaymentMicroservice\target\classes"
$aclForResources = Get-Acl $pathToResources

$AccessRuleForResorces1 = New-Object System.Security.AccessControl.FileSystemAccessRule($group, 'Read', 'Allow')
$aclForResources.SetAccessRule($AccessRuleForResorces1)

#$acl.RemoveAccessRule($AccessRule3)

$aclForJar | Set-Acl $pathToJar
$aclForResources | Set-Acl $pathToResources